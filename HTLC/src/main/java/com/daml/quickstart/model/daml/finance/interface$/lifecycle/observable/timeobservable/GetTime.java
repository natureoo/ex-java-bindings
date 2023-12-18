package com.daml.quickstart.model.daml.finance.interface$.lifecycle.observable.timeobservable;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetTime extends DamlRecord<GetTime> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final Set<String> actors;

  public GetTime(Set<String> actors) {
    this.actors = actors;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static GetTime fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<GetTime> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      Set<String> actors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      return new GetTime(actors);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("actors", this.actors.toValue(v$0 -> new Party(v$0))));
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
    if (!(object instanceof GetTime)) {
      return false;
    }
    GetTime other = (GetTime) object;
    return Objects.equals(this.actors, other.actors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actors);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.observable.timeobservable.GetTime(%s)",
        this.actors);
  }
}
