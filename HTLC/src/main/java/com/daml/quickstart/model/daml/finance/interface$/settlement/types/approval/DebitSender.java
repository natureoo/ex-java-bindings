package com.daml.quickstart.model.daml.finance.interface$.settlement.types.approval;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;

import java.util.Objects;

public class DebitSender extends Approval {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Unit unitValue;

  public DebitSender(Unit unitValue) {
    this.unitValue = unitValue;
  }

  public Variant toValue() {
    return new Variant("DebitSender", Unit.getInstance());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static DebitSender fromValue(Value value$) throws IllegalArgumentException {
    return (DebitSender)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof DebitSender)) {
      return false;
    }
    DebitSender other = (DebitSender) object;
    return Objects.equals(this.unitValue, other.unitValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unitValue);
  }

  @Override
  public String toString() {
    return String.format("DebitSender(%s)", this.unitValue);
  }
}
