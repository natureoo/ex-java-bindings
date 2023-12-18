package com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect;

import com.daml.ledger.javaapi.data.DamlCollectors;
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

public class CalculationResult extends DamlRecord<CalculationResult> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final List<Quantity<InstrumentKey, BigDecimal>> consumed;

  public final List<Quantity<InstrumentKey, BigDecimal>> produced;

  public CalculationResult(List<Quantity<InstrumentKey, BigDecimal>> consumed,
      List<Quantity<InstrumentKey, BigDecimal>> produced) {
    this.consumed = consumed;
    this.produced = produced;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static CalculationResult fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<CalculationResult> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      List<Quantity<InstrumentKey, BigDecimal>> consumed = PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(0).getValue());
      List<Quantity<InstrumentKey, BigDecimal>> produced = PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(1).getValue());
      return new CalculationResult(consumed, produced);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("consumed", this.consumed.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> new Numeric(v$2))))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("produced", this.produced.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> new Numeric(v$2))))));
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
    if (!(object instanceof CalculationResult)) {
      return false;
    }
    CalculationResult other = (CalculationResult) object;
    return Objects.equals(this.consumed, other.consumed) &&
        Objects.equals(this.produced, other.produced);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.consumed, this.produced);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.CalculationResult(%s, %s)",
        this.consumed, this.produced);
  }
}
