package com.daml.quickstart.model.daml.finance.interface$.settlement.types.allocation;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;

import java.util.Objects;

public class SettleOffledger extends Allocation {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Unit unitValue;

  public SettleOffledger(Unit unitValue) {
    this.unitValue = unitValue;
  }

  public Variant toValue() {
    return new Variant("SettleOffledger", Unit.getInstance());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static SettleOffledger fromValue(Value value$) throws IllegalArgumentException {
    return (SettleOffledger)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof SettleOffledger)) {
      return false;
    }
    SettleOffledger other = (SettleOffledger) object;
    return Objects.equals(this.unitValue, other.unitValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unitValue);
  }

  @Override
  public String toString() {
    return String.format("SettleOffledger(%s)", this.unitValue);
  }
}
