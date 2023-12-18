package com.daml.quickstart.model.daml.finance.interface$.settlement.types.allocation;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;

import java.util.Objects;

public class Pledge extends Allocation {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Base.ContractId contractIdValue;

  public Pledge(Base.ContractId contractIdValue) {
    this.contractIdValue = contractIdValue;
  }

  public Variant toValue() {
    return new Variant("Pledge", this.contractIdValue.toValue());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Pledge fromValue(Value value$) throws IllegalArgumentException {
    return (Pledge)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Pledge)) {
      return false;
    }
    Pledge other = (Pledge) object;
    return Objects.equals(this.contractIdValue, other.contractIdValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.contractIdValue);
  }

  @Override
  public String toString() {
    return String.format("Pledge(%s)", this.contractIdValue);
  }
}
