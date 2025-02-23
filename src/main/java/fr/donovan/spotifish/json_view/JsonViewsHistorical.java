package fr.donovan.spotifish.json_view;

public class JsonViewsHistorical {
    
    public interface AllHistorical extends Uuid, NumberOflisten, ListenAt, User, Song, Slug {}
    public interface MinimalHistorical extends Uuid, NumberOflisten, ListenAt, Slug {}

    public interface AllHistoricalWithoutId extends NumberOflisten, ListenAt, User, Song, Slug {}
    public interface MinimalHistoricalWithoutId extends NumberOflisten, ListenAt, Slug {}

    public interface Uuid  {}
    public interface NumberOflisten  {}
    public interface ListenAt  {}
    public interface User  {}
    public interface Song  {}
    public interface Slug  {}
}