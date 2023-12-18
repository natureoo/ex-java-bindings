package com.daml.quickstart.model.daml.finance.interface$.settlement.types.allocation;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;

import java.util.Objects;

public class Unallocated extends Allocation {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Unit unitValue;

  public Unallocated(Unit unitValue) {
    this.unitValue = unitValue;
  }

  public Variant toValue() {
    return new Variant("Unallocated", Unit.getInstance());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Unallocated fromValue(Value value$) throws IllegalArgumentException {
    return (Unallocated)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Unallocated)) {
      return false;
    }
    Unallocated other = (Unallocated) object;
    return Objects.equals(this.unitValue, other.unitValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unitValue);
  }

  @Override
  public String toString() {
    return String.format("Unallocated(%s)", this.unitValue);
  }
}
