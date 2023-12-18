package com.daml.quickstart.model.da.set.types;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Set<k> {
  public static final String _packageId = "97b883cd8a2b7f49f90d5d39c981cf6e110cf1f1c64427a28a6d58ec88c43657";

  public final Map<k, Unit> map;

  public Set(Map<k, Unit> map) {
    this.map = map;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static <k> Set<k> fromValue(Value value$, Function<Value, k> fromValuek) throws
      IllegalArgumentException {
    return Set.<k>valueDecoder(ValueDecoder.fromFunction(fromValuek)).decode(value$);
  }

  public static <k> ValueDecoder<Set<k>> valueDecoder(ValueDecoder<k> fromValuek) throws
      IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1, recordValue$);
      Map<k, Unit> map = PrimitiveValueDecoders.fromGenMap(fromValuek,
            PrimitiveValueDecoders.fromUnit).decode(fields$.get(0).getValue());
      return new Set<k>(map);
    } ;
  }

  public DamlRecord toValue(Function<k, Value> toValuek) {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(1);
    fields.add(new DamlRecord.Field("map", this.map.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> toValuek.apply(v$0.getKey()), v$0 -> Unit.getInstance()))));
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
    if (!(object instanceof Set<?>)) {
      return false;
    }
    Set<?> other = (Set<?>) object;
    return Objects.equals(this.map, other.map);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.map);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.da.set.types.Set(%s)", this.map);
  }
}
