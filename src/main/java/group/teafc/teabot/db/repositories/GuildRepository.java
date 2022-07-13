package group.teafc.teabot.db.repositories;

import group.teafc.teabot.db.entities.GuildEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends MongoRepository<GuildEntity, ObjectId> {
    GuildEntity findByGuildId(ObjectId guildId);
}
