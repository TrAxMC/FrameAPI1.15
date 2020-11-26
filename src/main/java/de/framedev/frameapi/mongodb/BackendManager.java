package de.framedev.frameapi.mongodb;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertOneOptions;
import de.framedev.frameapi.main.Main;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class BackendManager {
    private Main plugin;

    public enum DATA {
        NAME("name"),
        MONEY("money"),
        DAMAGE("damage"),
        ENTITYKILLS("entityKills"),
        DEATHS("deaths"),
        BLOCKSBROKEN("blocksBroken"),
        BLOCKSPLACEN("blocksPlacen"),
        COMMANDSUSED("commandsUsed"),
        KEY("key"),
        ENTITYTYPES("entityTypes"),
        OFFLINE("offline"),
        SLEEPTIMES("sleepTimes"),
        CREATEDATE("createDate"),
        LASTLOGIN("lastLogin"),
        LASTLOGOUT("lastLogout");

        private final String name;

        DATA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public BackendManager(Main plugin) {
        this.plugin = plugin;
    }

    public void createUserMoney(OfflinePlayer player, String collection) {
        String uuid = player.getUniqueId().toString();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document result = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (result == null) {
                Document dc = (new Document("uuid", uuid))
                        .append("name", player.getName())
                        .append("groupid", 0)
                        .append("money", 0)
                        .append("bank", 0)
                        .append("offline", false)
                        .append("createDate", System.currentTimeMillis())
                        .append("lastLogin", System.currentTimeMillis());
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        } else {
            this.plugin.getMongoManager().getDatabase().createCollection(collection);
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document result = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (result == null) {


                Document dc = (new Document("uuid", uuid))
                        .append("name", player.getName()).append("groupid", 0)
                        .append("money", 0)
                        .append("bank", 0)
                        .append("offline", false)
                        .append("createDate", System.currentTimeMillis())
                        .append("lastLogin", System.currentTimeMillis());
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        }
    }

    public void createPlayerStats(OfflinePlayer player, String collection) {
        String uuid = player.getUniqueId().toString();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document result = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (result == null) {


                Document dc = (new Document("uuid", uuid)).append("name", player.getName()).append("groupid", Integer.valueOf(0)).append("kills", Integer.valueOf(0)).append("createDate", Long.valueOf(System.currentTimeMillis())).append("lastLogin", Long.valueOf(System.currentTimeMillis()));
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(Boolean.valueOf(false)));
            }
        } else {
            this.plugin.getMongoManager().getDatabase().createCollection(collection);
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document result = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (result == null) {


                Document dc = (new Document("uuid", uuid)).append("name", player.getName()).append("groupid", Integer.valueOf(0)).append("kills", Integer.valueOf(0)).append("createDate", Long.valueOf(System.currentTimeMillis())).append("lastLogin", Long.valueOf(System.currentTimeMillis()));
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(Boolean.valueOf(false)));
            }
        }
    }


    public void updateUser(OfflinePlayer player, String where, Object data, String collection) {
        if (existsCollection(collection)) {
            String uuid = player.getUniqueId().toString();
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document document = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (document != null) {
                Document document1 = new Document(where, data);
                Document document2 = new Document("$set", document1);
                collections.updateOne((Bson) document, (Bson) document2);
            }
        } else {
            String uuid = player.getUniqueId().toString();
            this.plugin.getMongoManager().getDatabase().createCollection(collection);
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document document = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (document != null) {
                Document document1 = new Document(where, data);
                Document document2 = new Document("$set", document1);
                collections.updateOne((Bson) document, (Bson) document2);
            }
        }
    }


    public void deleteUser(OfflinePlayer player, String collection) {
        if (existsCollection(collection)) {
            String uuid = player.getUniqueId().toString();
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document document = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (document != null) {
                collections.deleteOne((Bson) document);
            }
        } else {
            String uuid = player.getUniqueId().toString();
            this.plugin.getMongoManager().getDatabase().createCollection(collection);
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            Document document = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
            if (document != null) {
                collections.deleteOne((Bson) document);
            }
        }
    }

    public Object get(OfflinePlayer player, String where, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> mongoCollection = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            String str = player.getUniqueId().toString();
            Document document1 = (Document) mongoCollection.find((Bson) new Document("uuid", str)).first();
            if (document1 != null) {
                return document1.get(where);
            }
            return null;
        }
        this.plugin.getMongoManager().getDatabase().createCollection(collection);
        MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
        String uuid = player.getUniqueId().toString();
        Document document = (Document) collections.find((Bson) new Document("uuid", uuid)).first();
        if (document != null) {
            return document.get(where);
        }
        return null;
    }


    public boolean existsCollection(String collection) {
        MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
        return collections != null;
    }


    @SuppressWarnings("deprecation")
    public ArrayList<OfflinePlayer> getOfflinePlayers(String collection) {
        ArrayList<OfflinePlayer> players = new ArrayList<>();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.plugin.getMongoManager().getDatabase().getCollection(collection);
            collections.find(new Document("offline", true)).forEach((Block<? super Document>) document -> {
                if (document != null) {
                    UUID uuid = UUID.fromString(document.getString("uuid"));
                    players.add(Bukkit.getOfflinePlayer(uuid));
                }
            });
            return players;
        }
        return null;
    }
}


