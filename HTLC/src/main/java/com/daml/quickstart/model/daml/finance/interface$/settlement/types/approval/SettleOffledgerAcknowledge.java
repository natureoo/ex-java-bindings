package com.daml.quickstart.model.daml.finance.interface$.settlement.types.approval;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;

import java.util.Objects;

public class SettleOffledgerAcknowledge extends Approval {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Unit unitValue;

  public SettleOffledgerAcknowledge(Unit unitValue) {
    this.unitValue = unitValue;
  }

  public Variant toValue() {
    return new Variant("SettleOffledgerAcknowledge", Unit.getInstance());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static SettleOffledgerAcknowledge fromValue(Value value$) throws IllegalArgumentException {
    return (SettleOffledgerAcknowledge)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof SettleOffledgerAcknowledge)) {
      return false;
    }
    SettleOffledgerAcknowledge other = (SettleOffledgerAcknowledge) object;
    return Objects.equals(this.unitValue, other.unitValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unitValue);
  }

  @Override
  public String toString() {
    return String.format("SettleOffledgerAcknowledge(%s)", this.unitValue);
  }
}
