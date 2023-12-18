package com.daml.quickstart.model.daml.finance.interface$.settlement.types.approval;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.Objects;

public class TakeDelivery extends Approval {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final AccountKey accountKeyValue;

  public TakeDelivery(AccountKey accountKeyValue) {
    this.accountKeyValue = accountKeyValue;
  }

  public Variant toValue() {
    return new Variant("TakeDelivery", this.accountKeyValue.toValue());
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static TakeDelivery fromValue(Value value$) throws IllegalArgumentException {
    return (TakeDelivery)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof TakeDelivery)) {
      return false;
    }
    TakeDelivery other = (TakeDelivery) object;
    return Objects.equals(this.accountKeyValue, other.accountKeyValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.accountKeyValue);
  }

  @Override
  public String toString() {
    return String.format("TakeDelivery(%s)", this.accountKeyValue);
  }
}
