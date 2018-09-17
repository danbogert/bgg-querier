package com.yogurtpowered.bgg.api;

import com.yogurtpowered.bgg.api.model.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BggApi {

    private static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";

    private final RestTemplate restTemplate;

    public BggApi() {
        this.restTemplate = new RestTemplate();
    }

    public Items getOwnedItems(CollectionType collectionType, String username) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path("collection")
                .queryParam("username", username)
                .queryParam("own", "1")
                .queryParam("subtype", collectionType.getType());

        ResponseEntity<Items> response = restTemplate.getForEntity(builder.toUriString(), Items.class);


        System.out.println(response);

        return null;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Pass the username as a argument");
        }

        BggApi bggApi = new BggApi();
        bggApi.getOwnedItems(CollectionType.BOARDGAME, args[0]);
    }
}