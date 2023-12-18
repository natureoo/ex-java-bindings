package com.daml.quickstart.model.daml.finance.interface$.util.disclosure;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.da.types.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddObservers extends DamlRecord<AddObservers> {
  public static final String _packageId = "9e0b0b245207257617d2a345f960be47a5a046ff26f4227bbd237f5019ea1b2e";

  public final Set<String> disclosers;

  public final Tuple2<String, Set<String>> observersToAdd;

  public AddObservers(Set<String> disclosers, Tuple2<String, Set<String>> observersToAdd) {
    this.disclosers = disclosers;
    this.observersToAdd = observersToAdd;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static AddObservers fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<AddObservers> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      Set<String> disclosers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Tuple2<String, Set<String>> observersToAdd = Tuple2.<String,
          Set<String>>valueDecoder(PrimitiveValueDecoders.fromText,
          Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(1).getValue());
      return new AddObservers(disclosers, observersToAdd);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("disclosers", this.disclosers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("observersToAdd", this.observersToAdd.toValue(v$0 -> new Text(v$0),
        v$1 -> v$1.toValue(v$2 -> new Party(v$2)))));
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
    if (!(object instanceof AddObservers)) {
      return false;
    }
    AddObservers other = (AddObservers) object;
    return Objects.equals(this.disclosers, other.disclosers) &&
        Objects.equals(this.observersToAdd, other.observersToAdd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.disclosers, this.observersToAdd);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.util.disclosure.AddObservers(%s, %s)",
        this.disclosers, this.observersToAdd);
  }
}
