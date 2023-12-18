package com.daml.quickstart.model.daml.finance.settlement.hierarchy;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hierarchy extends DamlRecord<Hierarchy> {
  public static final String _packageId = "252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94";

  public final String rootCustodian;

  public final List<List<String>> pathsToRootCustodian;

  public Hierarchy(String rootCustodian, List<List<String>> pathsToRootCustodian) {
    this.rootCustodian = rootCustodian;
    this.pathsToRootCustodian = pathsToRootCustodian;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Hierarchy fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Hierarchy> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      String rootCustodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      List<List<String>> pathsToRootCustodian = PrimitiveValueDecoders.fromList(
            PrimitiveValueDecoders.fromList(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(1).getValue());
      return new Hierarchy(rootCustodian, pathsToRootCustodian);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("rootCustodian", new Party(this.rootCustodian)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("pathsToRootCustodian", this.pathsToRootCustodian.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.stream().collect(DamlCollectors.toDamlList(v$1 -> new Party(v$1)))))));
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
    if (!(object instanceof Hierarchy)) {
      return false;
    }
    Hierarchy other = (Hierarchy) object;
    return Objects.equals(this.rootCustodian, other.rootCustodian) &&
        Objects.equals(this.pathsToRootCustodian, other.pathsToRootCustodian);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.rootCustodian, this.pathsToRootCustodian);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.hierarchy.Hierarchy(%s, %s)",
        this.rootCustodian, this.pathsToRootCustodian);
  }
}
