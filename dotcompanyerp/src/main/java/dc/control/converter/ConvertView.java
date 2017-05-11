package dc.control.converter;

import java.math.BigDecimal;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@SuppressWarnings("serial")
public class ConvertView extends CustomComponent {

	public ConvertView() {

		FormLayout layout = new FormLayout();

		layout.setMargin(true);

		setCompositionRoot(layout);

		FieldGroup group = new FieldGroup();

		Account account = new Account();

		Money money = Money.of(CurrencyUnit.USD, new BigDecimal(1000));

		account.setBalance(money);

		group.setItemDataSource(new BeanItem<Account>(account));

		TextField field = (TextField) group.buildAndBind("Balance", "balance");

		field.setImmediate(true);
		field.setConverter(Money.class);

		layout.addComponent(field);
	}
}
