package com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View extends DamlRecord<View> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final Set<String> providers;

  public final Set<String> claimers;

  public final Set<String> settlers;

  public final RouteProvider.ContractId routeProviderCid;

  public final Factory.ContractId settlementFactoryCid;

  public View(Set<String> providers, Set<String> claimers, Set<String> settlers,
      RouteProvider.ContractId routeProviderCid, Factory.ContractId settlementFactoryCid) {
    this.providers = providers;
    this.claimers = claimers;
    this.settlers = settlers;
    this.routeProviderCid = routeProviderCid;
    this.settlementFactoryCid = settlementFactoryCid;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static View fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<View> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5,
          recordValue$);
      Set<String> providers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> claimers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(2).getValue());
      RouteProvider.ContractId routeProviderCid =
          new RouteProvider.ContractId(fields$.get(3).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected routeProviderCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Factory.ContractId settlementFactoryCid =
          new Factory.ContractId(fields$.get(4).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected settlementFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new View(providers, claimers, settlers, routeProviderCid, settlementFactoryCid);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(5);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("providers", this.providers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("claimers", this.claimers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("routeProviderCid", this.routeProviderCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlementFactoryCid", this.settlementFactoryCid.toValue()));
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
    if (!(object instanceof View)) {
      return false;
    }
    View other = (View) object;
    return Objects.equals(this.providers, other.providers) &&
        Objects.equals(this.claimers, other.claimers) &&
        Objects.equals(this.settlers, other.settlers) &&
        Objects.equals(this.routeProviderCid, other.routeProviderCid) &&
        Objects.equals(this.settlementFactoryCid, other.settlementFactoryCid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.providers, this.claimers, this.settlers, this.routeProviderCid,
        this.settlementFactoryCid);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim.View(%s, %s, %s, %s, %s)",
        this.providers, this.claimers, this.settlers, this.routeProviderCid,
        this.settlementFactoryCid);
  }
}
