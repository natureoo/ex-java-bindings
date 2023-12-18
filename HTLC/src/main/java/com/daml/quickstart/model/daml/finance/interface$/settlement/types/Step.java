package com.daml.quickstart.model.daml.finance.interface$.settlement.types;

import com.daml.ledger.javaapi.data.Numeric;
import com.daml.ledger.javaapi.data.Party;
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

public class Step extends DamlRecord<Step> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final String sender;

  public final String receiver;

  public final Quantity<InstrumentKey, BigDecimal> quantity;

  public Step(String sender, String receiver, Quantity<InstrumentKey, BigDecimal> quantity) {
    this.sender = sender;
    this.receiver = receiver;
    this.quantity = quantity;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Step fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Step> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      String sender = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String receiver = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Quantity<InstrumentKey, BigDecimal> quantity =
          Quantity.<InstrumentKey,
          BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
          PrimitiveValueDecoders.fromNumeric).decode(fields$.get(2).getValue());
      return new Step(sender, receiver, quantity);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("sender", new Party(this.sender)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("receiver", new Party(this.receiver)));
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
    if (!(object instanceof Step)) {
      return false;
    }
    Step other = (Step) object;
    return Objects.equals(this.sender, other.sender) &&
        Objects.equals(this.receiver, other.receiver) &&
        Objects.equals(this.quantity, other.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sender, this.receiver, this.quantity);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.types.Step(%s, %s, %s)",
        this.sender, this.receiver, this.quantity);
  }
}
