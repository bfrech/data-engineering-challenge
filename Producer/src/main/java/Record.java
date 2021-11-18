public class Record {
    private String schema;
    private String id;
    private String type;
    private String namespace;
    private String title;
    private String comment;
    private String timestamp;
    private String user;
    private String bot;
    private String minor;
    private String patrolled;
    private String serverURL;
    private String serverName;
    private String serverScriptPath;
    private String wiki;
    private String parsedocument;
    private String metaDomain;
    private String metaURL;
    private String metaRequestId;
    private String metaStream;
    private String metaTopic;
    private String metaDT;
    private String metaPartition;
    private String metaOffset;
    private String metaId;
    private String lengthOld;
    private String lengthNew;
    private String revisionOld;
    private String revisionNew;


    public Record(String schema, String id, String type, String namespace, String title, String comment, String timestamp, String user, String bot, String minor, String patrolled, String serverURL, String serverName, String serverScriptPath, String wiki, String parsedocument, String metaDomain, String metaURL, String metaRequestId, String metaStream, String metaTopic, String metaDT, String metaPartition, String metaOffset, String metaId, String lengthOld, String lengthNew, String revisionOld, String revisionNew) {
        this.schema = schema;
        this.id = id;
        this.type = type;
        this.namespace = namespace;
        this.title = title;
        this.comment = comment;
        this.timestamp = timestamp;
        this.user = user;
        this.bot = bot;
        this.minor = minor;
        this.patrolled = patrolled;
        this.serverURL = serverURL;
        this.serverName = serverName;
        this.serverScriptPath = serverScriptPath;
        this.wiki = wiki;
        this.parsedocument = parsedocument;
        this.metaDomain = metaDomain;
        this.metaURL = metaURL;
        this.metaRequestId = metaRequestId;
        this.metaStream = metaStream;
        this.metaTopic = metaTopic;
        this.metaDT = metaDT;
        this.metaPartition = metaPartition;
        this.metaOffset = metaOffset;
        this.metaId = metaId;
        this.lengthOld = lengthOld;
        this.lengthNew = lengthNew;
        this.revisionOld = revisionOld;
        this.revisionNew = revisionNew;
    }
}


