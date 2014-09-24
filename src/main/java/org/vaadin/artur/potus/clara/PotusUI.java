package org.vaadin.artur.potus.clara;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import org.vaadin.artur.potus.clara.data.Party;
import org.vaadin.artur.potus.clara.data.President;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiDataSource;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("valo")
public class PotusUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PotusUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT,
			new Locale("fi", "FI"));

	private BeanFieldGroup<President> fieldGroup = new BeanFieldGroup<President>(
			President.class);

	private static class HackFactory extends DefaultFieldGroupFieldFactory {
		@Override
		public void populateWithEnumData(com.vaadin.ui.AbstractSelect select,
				java.lang.Class<? extends Enum> enumClass) {
			super.populateWithEnumData(select, enumClass);
		};
	};

	@Override
	protected void init(VaadinRequest request) {
		// 1. Create layout
		setContent(Clara.create("potus-crud.xml", this));

		// 2. Configure table
		Table table = getComponent("table");
		table.addGeneratedColumn("inOffice", new ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				President p = (President) itemId;
				return formatter.format(p.getTookOffice()) + " - "
						+ formatter.format(p.getLeftOffice());
			}
		});
		table.setVisibleColumns("firstName", "lastName", "party", "inOffice");
		table.setColumnHeaders("First name", "Last name", "Party", "In office");

		// 3. Configure field binding
		// Clara could help with this ..
		fieldGroup.bind(getComponent("firstName"), "firstName");
		fieldGroup.bind(getComponent("lastName"), "lastName");
		fieldGroup.bind(getComponent("party"), "party");
		fieldGroup.bind(getComponent("tookOffice"), "tookOffice");
		fieldGroup.bind(getComponent("leftOffice"), "leftOffice");

		// 4. Populate party select
		new HackFactory().populateWithEnumData(getComponent("party"),
				Party.class);

		// 6. Bind edit function
		table.addValueChangeListener((e) -> {
			Item v = table.getItem(table.getValue());
			// This if is needed because of #14731
			if (v != null) {
				fieldGroup.setItemDataSource(v);
			} else {
				fieldGroup.setItemDataSource(new President("", "", null, null,
						null));
			}
		});

		// 7. Bind save/cancel/delete logic
		((Button) getComponent("save")).addClickListener((e) -> {
			try {
				fieldGroup.commit();
				table.setValue(null);
			} catch (CommitException e1) {
				Notification.show(e1.getMessage());
			}
		});
		((Button) getComponent("revert")).addClickListener((e) -> {
			fieldGroup.discard();
		});
		((Button) getComponent("delete")).addClickListener((e) -> {
			table.removeItem(table.getValue());
		});

	}

	private <T extends Component> T getComponent(String string) {
		return (T) Clara.findComponentById(getContent(), string);
	}

	@UiDataSource("table")
	public Container.Indexed createData() {
		BeanItemContainer<President> data = new BeanItemContainer<President>(
				President.class);
		data.addBean(new President("George", "Washington", Party.INDEPENDENT,
				createDate(1789, 4, 30), createDate(1797, 3, 4)));
		data.addBean(new President("John", "Adams", Party.FEDERALIST,
				createDate(1797, 3, 4), createDate(1801, 3, 4)));
		data.addBean(new President("Thomas", "Jefferson",
				Party.DEMOCRAT_REPUBLICAN, createDate(1801, 3, 4), createDate(
						1809, 3, 4)));
		data.addBean(new President("James", "Madison",
				Party.DEMOCRAT_REPUBLICAN, createDate(1809, 3, 4), createDate(
						1812, 4, 20)));
		data.addBean(new President("James", "Monroe",
				Party.DEMOCRAT_REPUBLICAN, createDate(1817, 3, 4), createDate(
						1825, 3, 4)));
		return data;
	}

	private Date createDate(int y, int m, int d) {
		return new Date(y - 1900, m - 1, d);
	}

}