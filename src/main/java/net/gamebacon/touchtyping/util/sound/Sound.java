package net.gamebacon.touchtyping.util.sound;

public enum Sound {

    KEY_TYPED("key_typed", 3, "wav"),
    KEY_PUSH("key_push", 3, "wav"),
    KEY_RELEASE("key_release", 3, "wav"),
    ERROR("error", 1, "wav");

    private Sound(String name, int variants, String extension) {
        this.name = name;
        this.variants = variants;
        this.extension = extension;
    }

    private final String name;
    private final int variants;
    private final String extension;


    @Override
    public String toString() {
        int var = (int) (Math.random() * variants) + 1;
        return String.format("/sounds/%s%d.%s",name, var, extension);
    }
}
