package com.daml.quickstart.model.daml.finance.interface$.settlement.instruction;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Execute extends DamlRecord<Execute> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Set<String> actors;

  public Execute(Set<String> actors) {
    this.actors = actors;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Execute fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Execute> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      Set<String> actors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      return new Execute(actors);
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
    if (!(object instanceof Execute)) {
      return false;
    }
    Execute other = (Execute) object;
    return Objects.equals(this.actors, other.actors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actors);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Execute(%s)",
        this.actors);
  }
}
