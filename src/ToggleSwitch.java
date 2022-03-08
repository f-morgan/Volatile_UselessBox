public class ToggleSwitch {
    volatile private boolean toggleSwitch = false;

    public boolean isToggleSwitch() {
        return toggleSwitch;
    }

    public void setToggleSwitch(boolean toggleSwitch) {
        this.toggleSwitch = toggleSwitch;
    }
}
