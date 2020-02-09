package bowling.io.pojo;

import static bowling.utils.ValidationUtils.checkNotNullNorBlank;

public class PlayerShoot {

    private String name;
    private String shootDesc;

    public static PlayerShoot fromStringParts(String[] parts) {
        return new PlayerShoot(parts[0], parts[1]);
    }

    public PlayerShoot(String name, String shootDesc) {
        checkNotNullNorBlank(name, "Player name cannot be null nor blank");
        checkNotNullNorBlank(shootDesc, "Shoot value description cannot be null nor blank");
        this.name = name;
        this.shootDesc = shootDesc;
    }

    public String getName() {
        return name;
    }

    public String getShootDesc() {
        return shootDesc;
    }
}
