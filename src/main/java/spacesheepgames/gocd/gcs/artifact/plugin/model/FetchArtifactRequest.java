package spacesheepgames.gocd.gcs.artifact.plugin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import spacesheepgames.gocd.gcs.artifact.plugin.utils.Util;

import java.util.Map;

public class FetchArtifactRequest {
    @Expose
    @SerializedName("fetch_artifact_configuration")
    private FetchArtifactConfig fetchArtifactConfig;
    @Expose
    @SerializedName("store_configuration")
    private ArtifactStoreConfig artifactStoreConfig;
    @Expose
    @SerializedName("artifact_metadata")
    private Map<String, Object> metadata;

    @Expose
    @SerializedName("agent_working_directory")
    private String agentWorkingDir;

    public FetchArtifactRequest() {
    }

    public FetchArtifactRequest(ArtifactStoreConfig artifactStoreConfig, Map<String, Object> metadata, FetchArtifactConfig fetchArtifactConfig, String agentWorkingDir) {
        this.artifactStoreConfig = artifactStoreConfig;
        this.metadata = metadata;
        this.agentWorkingDir = agentWorkingDir;
        this.fetchArtifactConfig = fetchArtifactConfig;
    }

    public ArtifactStoreConfig getArtifactStoreConfig() {
        return artifactStoreConfig;
    }

    public String getAgentWorkingDir() {
        return agentWorkingDir;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static FetchArtifactRequest fromJSON(String json) {
        return Util.GSON.fromJson(json, FetchArtifactRequest.class);
    }

    public FetchArtifactConfig getFetchArtifactConfig() {
        return fetchArtifactConfig;
    }
}
