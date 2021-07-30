package pojo;

public class SMSSent {
    private BodyAnalysis bodyAnalysis;

    private String createdAt;

    private String from;

    private String to;

    private String body;

    private String trackingId;

    private String status;

    private Boolean flash;

    public BodyAnalysis getBodyAnalysis() {
        return bodyAnalysis;
    }

    public void setBodyAnalysis(BodyAnalysis bodyAnalysis) {
        this.bodyAnalysis = bodyAnalysis;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFlash() {
        return flash;
    }

    public void setFlash(Boolean flash) {
        this.flash = flash;
    }

    @Override
    public String toString() {
        return "ClassPojo [bodyAnalysis = " + bodyAnalysis + ", createdAt = " + createdAt + ", from = " + from + ", to = " + to + ", body = " + body + ", trackingId = " + trackingId + ", status = " + status + ", flash = " + flash + "]";
    }
}
