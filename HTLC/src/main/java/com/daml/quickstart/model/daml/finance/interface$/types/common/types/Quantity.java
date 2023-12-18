package com.daml.quickstart.model.daml.finance.interface$.types.common.types;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Quantity<u, a> {
  public static final String _packageId = "361a5742b581d2d0dc1378f905a7f0ff692d3eb0d79b9fde51e34390813cf7d4";

  public final u unit;

  public final a amount;

  public Quantity(u unit, a amount) {
    this.unit = unit;
    this.amount = amount;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static <u, a> Quantity<u, a> fromValue(Value value$, Function<Value, u> fromValueu,
      Function<Value, a> fromValuea) throws IllegalArgumentException {
    return Quantity.<u, a>valueDecoder(ValueDecoder.fromFunction(fromValueu),
          ValueDecoder.fromFunction(fromValuea)).decode(value$);
  }

  public static <u, a> ValueDecoder<Quantity<u, a>> valueDecoder(ValueDecoder<u> fromValueu,
      ValueDecoder<a> fromValuea) throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2, recordValue$);
      u unit = fromValueu.decode(fields$.get(0).getValue());
      a amount = fromValuea.decode(fields$.get(1).getValue());
      return new Quantity<u, a>(unit, amount);
    } ;
  }

  public DamlRecord toValue(Function<u, Value> toValueu, Function<a, Value> toValuea) {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(2);
    fields.add(new DamlRecord.Field("unit", toValueu.apply(this.unit)));
    fields.add(new DamlRecord.Field("amount", toValuea.apply(this.amount)));
    return new DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Quantity<?, ?>)) {
      return false;
    }
    Quantity<?, ?> other = (Quantity<?, ?>) object;
    return Objects.equals(this.unit, other.unit) && Objects.equals(this.amount, other.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unit, this.amount);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity(%s, %s)",
        this.unit, this.amount);
  }
}
