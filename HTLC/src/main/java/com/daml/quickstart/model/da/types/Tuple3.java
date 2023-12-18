package com.daml.quickstart.model.da.types;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Tuple3<t1, t2, t3> {
  public static final String _packageId = "40f452260bef3f29dede136108fc08a88d5a5250310281067087da6f0baddff7";

  public final t1 _1;

  public final t2 _2;

  public final t3 _3;

  public Tuple3(t1 _1, t2 _2, t3 _3) {
    this._1 = _1;
    this._2 = _2;
    this._3 = _3;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static <t1, t2, t3> Tuple3<t1, t2, t3> fromValue(Value value$,
      Function<Value, t1> fromValuet1, Function<Value, t2> fromValuet2,
      Function<Value, t3> fromValuet3) throws IllegalArgumentException {
    return Tuple3.<t1, t2, t3>valueDecoder(ValueDecoder.fromFunction(fromValuet1),
          ValueDecoder.fromFunction(fromValuet2), ValueDecoder.fromFunction(fromValuet3))
        .decode(value$);
  }

  public static <t1, t2, t3> ValueDecoder<Tuple3<t1, t2, t3>> valueDecoder(
      ValueDecoder<t1> fromValuet1, ValueDecoder<t2> fromValuet2, ValueDecoder<t3> fromValuet3)
      throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3, recordValue$);
      t1 _1 = fromValuet1.decode(fields$.get(0).getValue());
      t2 _2 = fromValuet2.decode(fields$.get(1).getValue());
      t3 _3 = fromValuet3.decode(fields$.get(2).getValue());
      return new Tuple3<t1, t2, t3>(_1, _2, _3);
    } ;
  }

  public DamlRecord toValue(Function<t1, Value> toValuet1, Function<t2, Value> toValuet2,
      Function<t3, Value> toValuet3) {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(3);
    fields.add(new DamlRecord.Field("_1", toValuet1.apply(this._1)));
    fields.add(new DamlRecord.Field("_2", toValuet2.apply(this._2)));
    fields.add(new DamlRecord.Field("_3", toValuet3.apply(this._3)));
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
    if (!(object instanceof Tuple3<?, ?, ?>)) {
      return false;
    }
    Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) object;
    return Objects.equals(this._1, other._1) && Objects.equals(this._2, other._2) &&
        Objects.equals(this._3, other._3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this._1, this._2, this._3);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.da.types.Tuple3(%s, %s, %s)", this._1, this._2,
        this._3);
  }
}
