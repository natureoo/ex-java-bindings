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

public class RoutedStep extends DamlRecord<RoutedStep> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final String sender;

  public final String receiver;

  public final String custodian;

  public final Quantity<InstrumentKey, BigDecimal> quantity;

  public RoutedStep(String sender, String receiver, String custodian,
      Quantity<InstrumentKey, BigDecimal> quantity) {
    this.sender = sender;
    this.receiver = receiver;
    this.custodian = custodian;
    this.quantity = quantity;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static RoutedStep fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<RoutedStep> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(4,
          recordValue$);
      String sender = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String receiver = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(2).getValue());
      Quantity<InstrumentKey, BigDecimal> quantity =
          Quantity.<InstrumentKey,
          BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
          PrimitiveValueDecoders.fromNumeric).decode(fields$.get(3).getValue());
      return new RoutedStep(sender, receiver, custodian, quantity);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(4);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("sender", new Party(this.sender)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("receiver", new Party(this.receiver)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("custodian", new Party(this.custodian)));
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
    if (!(object instanceof RoutedStep)) {
      return false;
    }
    RoutedStep other = (RoutedStep) object;
    return Objects.equals(this.sender, other.sender) &&
        Objects.equals(this.receiver, other.receiver) &&
        Objects.equals(this.custodian, other.custodian) &&
        Objects.equals(this.quantity, other.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sender, this.receiver, this.custodian, this.quantity);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep(%s, %s, %s, %s)",
        this.sender, this.receiver, this.custodian, this.quantity);
  }
}
