package org.example;

import org.apache.lucene.util.SloppyMath;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Product shoes = Product.builder().name("Shoes").sku("SH001").build();
        Product computer = Product.builder().name("Computer").sku("CM001").build();
        Product phone = Product.builder().name("Phone").sku("PH001").build();
        Product book = Product.builder().name("Book").sku("BK001").build();
        Product camera = Product.builder().name("Camera").sku("CA001").build();

        Store store1 = Store.builder()
                .name("Store 1")
                .storeCode("ST001")
                .zipcode("12345")
                .build();

        Store store2 = Store.builder()
                .name("Store 2")
                .storeCode("ST002")
                .zipcode("23456")
                .build();

        Store store3 = Store.builder()
                .name("Store 3")
                .storeCode("ST003")
                .zipcode("34567")
                .build();

        Store store4 = Store.builder()
                .name("Store 4")
                .storeCode("ST004")
                .zipcode("45678")
                .build();

        Store store5 = Store.builder()
                .name("Store 5")
                .storeCode("ST005")
                .zipcode("56789")
                .build();


        Zipcode zipcode1 = Zipcode.builder()
                .zipcode("12345")
                .lat(40.712776)
                .lng(-74.005974)
                .build();

        Zipcode zipcode2 = Zipcode.builder()
                .zipcode("23456")
                .lat(34.052235)
                .lng(-118.243683)
                .build();

        Zipcode zipcode3 = Zipcode.builder()
                .zipcode("34567")
                .lat(41.878113)
                .lng(-87.629799)
                .build();

        Zipcode zipcode4 = Zipcode.builder()
                .zipcode("45678")
                .lat(51.507351)
                .lng(-0.127758)
                .build();

        Zipcode zipcode5 = Zipcode.builder()
                .zipcode("56789")
                .lat(48.856613)
                .lng(2.352222)
                .build();

        Zipcode zipcode6 = Zipcode.builder()
                .zipcode("56790")
                .lat(49.856613)
                .lng(2.352222)
                .build();

        Inventory inventory1Store1 = Inventory.builder()
                .storeCode("ST001")
                .sku("SH001")
                .quantity(10)
                .build();

        Inventory inventory2Store1 = Inventory.builder()
                .storeCode("ST001")
                .sku("CM001")
                .quantity(20)
                .build();

        Inventory inventory1Store2 = Inventory.builder()
                .storeCode("ST002")
                .sku("SH001")
                .quantity(15)
                .build();

        Inventory inventory2Store2 = Inventory.builder()
                .storeCode("ST002")
                .sku("CM001")
                .quantity(25)
                .build();

        Inventory inventory1Store3 = Inventory.builder()
                .storeCode("ST003")
                .sku("SH001")
                .quantity(8)
                .build();

        Inventory inventory2Store3 = Inventory.builder()
                .storeCode("ST003")
                .sku("CM001")
                .quantity(18)
                .build();

        Inventory inventory1Store4 = Inventory.builder()
                .storeCode("ST004")
                .sku("SH001")
                .quantity(12)
                .build();

        Inventory inventory2Store4 = Inventory.builder()
                .storeCode("ST004")
                .sku("CM001")
                .quantity(22)
                .build();

        Inventory inventory1Store5 = Inventory.builder()
                .storeCode("ST005")
                .sku("SH001")
                .quantity(7)
                .build();

        Inventory inventory2Store5 = Inventory.builder()
                .storeCode("ST005")
                .sku("CM001")
                .quantity(17)
                .build();

        List<Inventory> inventoryList = List.of(inventory1Store1, inventory2Store1, inventory1Store2, inventory2Store2, inventory1Store3, inventory2Store3, inventory1Store4, inventory2Store4, inventory1Store5, inventory2Store5);

        Buyer buyer1 = Buyer.builder()
                .name("buyer1")
                .zipcode("56790")
                .sku("CM001")
                .build();


        //SOLUTION
        List<Store> storeList = List.of(store1, store2, store3, store4, store5);
        List<Zipcode> zipcodeList = List.of(zipcode1, zipcode2, zipcode3, zipcode4, zipcode5, zipcode6);
        String targetZipcodeString = "56790";

        List<Store> result = storeList.stream()
                .filter(s -> {
                    Double[] latLngTarget = zipcodeList.stream()
                            .filter(z -> z.getZipcode().equals(buyer1.getZipcode()))
                            .findFirst()
                            .map(z -> new Double[]{z.getLat(), z.getLng()})
                            .orElseThrow(() -> new RuntimeException("Can not find zipcode."));

                    Double[] latLngStore = zipcodeList.stream()
                            .filter(z -> z.getZipcode().equals(s.getZipcode()))
                            .findFirst()
                            .map(z -> new Double[]{z.getLat(), z.getLng()})
                            .orElseThrow(() -> new RuntimeException("Can not find zipcode."));

                    return SloppyMath.haversinMeters(latLngStore[0], latLngStore[1], latLngTarget[0], latLngTarget[1]) < 300000;
                })
                .filter(s -> inventoryList.stream()
                        .anyMatch(i -> i.getStoreCode().equals(s.getStoreCode()) &&
                                i.getSku().equals(buyer1.getSku()) &&
                                i.getQuantity() > 0
                        ))
                .toList();

        System.out.println(result);


//        Zipcode targetZipcode = zipcodeList.stream()
//                .filter(z -> z.getZipcode().equals(targetZipcodeString))
//                .findFirst()
//                .orElse(null);
//
//        List<Zipcode> nearbyZipcodes = zipcodeList.stream()
//                .filter(z -> SloppyMath.haversinMeters(
//                        z.getLat(), z.getLng(), targetZipcode.getLat(), targetZipcode.getLng()
//                ) < 300000)
//                .toList();
//
//        List<String> nearbyZipcodeString = nearbyZipcodes.stream()
//                .map(Zipcode::getZipcode)
//                .toList();
//
//        List<Store> nearbyStore = storeList.stream()
//                .filter(s -> nearbyZipcodeString.contains(s.getZipcode()))
//                .toList();
//
//        List<String> nearbyStoreCode = nearbyStore.stream()
//                .map(s -> s.getStoreCode())
//                .toList();
//
//        List<Inventory> nearByInventory = inventoryList.stream()
//                .filter(i -> nearbyStoreCode.contains(i.getStoreCode()))
//                .filter(i -> i.getSku().equals(buyer1.getSku()))
//                .filter(i -> i.getQuantity() > 0)
//                .toList();
//
//        System.out.println(nearByInventory);
    }

}