package com;
public enum OperationSystem {

	WINDOWS, LINUX, MAC, OTHER;

	public static OperationSystem getOS() {
        String str = System.getProperty("os.name").toLowerCase();
		if (str.contains("win"))
			return OperationSystem.WINDOWS;
		if (str.contains("mac"))
			return OperationSystem.MAC;
		if (str.contains("nix") || str.contains("nux"))
			return OperationSystem.LINUX;
		return OperationSystem.OTHER;
	}

}
