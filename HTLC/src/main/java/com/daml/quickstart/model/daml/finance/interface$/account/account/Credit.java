package com.daml.quickstart.model.daml.finance.interface$.account.account;

import com.daml.ledger.javaapi.data.Numeric;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Credit extends DamlRecord<Credit> {
  public static final String _packageId = "615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2";

  public final Quantity<InstrumentKey, BigDecimal> quantity;

  public Credit(Quantity<InstrumentKey, BigDecimal> quantity) {
    this.quantity = quantity;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Credit fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Credit> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      Quantity<InstrumentKey, BigDecimal> quantity =
          Quantity.<InstrumentKey,
          BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
          PrimitiveValueDecoders.fromNumeric).decode(fields$.get(0).getValue());
      return new Credit(quantity);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("quantity", this.quantity.toValue(v$0 -> v$0.toValue(),
        v$1 -> new Numeric(v$1))));
    return new com.daml.ledger.javaapi.data.DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Credit)) {
      return false;
    }
    Credit other = (Credit) object;
    return Objects.equals(this.quantity, other.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.quantity);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.account.account.Credit(%s)",
        this.quantity);
  }
}
