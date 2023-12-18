package com.daml.quickstart.model.daml.finance.interface$.settlement.types.allocation;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.InstructionKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.Objects;

public class PassThroughFrom extends Allocation {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Tuple2<AccountKey, InstructionKey> tuple2Value;

  public PassThroughFrom(Tuple2<AccountKey, InstructionKey> tuple2Value) {
    this.tuple2Value = tuple2Value;
  }

  public Variant toValue() {
    return new Variant("PassThroughFrom", this.tuple2Value.toValue(v$0 -> v$0.toValue(),
        v$1 -> v$1.toValue()));
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static PassThroughFrom fromValue(Value value$) throws IllegalArgumentException {
    return (PassThroughFrom)valueDecoder().decode(value$);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof PassThroughFrom)) {
      return false;
    }
    PassThroughFrom other = (PassThroughFrom) object;
    return Objects.equals(this.tuple2Value, other.tuple2Value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tuple2Value);
  }

  @Override
  public String toString() {
    return String.format("PassThroughFrom(%s)", this.tuple2Value);
  }
}
