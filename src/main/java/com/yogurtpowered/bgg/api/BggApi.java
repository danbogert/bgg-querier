package com.yogurtpowered.bgg.api;

import com.yogurtpowered.bgg.api.model.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BggApi {

    private static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";

    private final RestClient restClient;

    public BggApi() {
        this.restClient = new RestClient();
    }

    public Items getOwnedItems(CollectionType collectionType, String username) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path("collection")
                .queryParam("username", username)
                .queryParam("own", "1")
                .queryParam("subtype", collectionType.getType());

        return restClient.getWithRetry(builder.toUriString(), Items.class);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Pass the username as a argument");
        }

        BggApi bggApi = new BggApi();
        Items items = bggApi.getOwnedItems(CollectionType.VIDEOGAME, args[0]);

        System.out.println(items);
    }
}