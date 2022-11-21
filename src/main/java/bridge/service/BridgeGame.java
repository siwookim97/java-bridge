package bridge.service;

import bridge.BridgeRandomNumberGenerator;
import bridge.util.Utils;
import bridge.util.validator.BridgeMakerValidator;
import bridge.util.validator.BridgeMoveValidator;
import bridge.util.validator.BridgeRetryValidator;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static boolean gameSuccess = false;
    private static int gameCount = 0;

    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    private ComputerBridge computerBridge = new ComputerBridge();
    private UserBridge userBridge = new UserBridge();

    // 다리 생성
    public void createBridge(String length) {
        new BridgeMakerValidator(length);
        computerBridge.setBridge(bridgeMaker.makeBridge(Utils.convertToInt(length)));
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    // 다리 이동 후 정답 판별 하기
    public boolean move(String location) {
        new BridgeMoveValidator(location);
        userBridge.addBridge(location);
        if (isGameSuccess()) {
            return true;
        }
        if (isGameFail()) {
            return true;
        }

        return false;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    //
    public boolean retry(String retryMessage) {
        new BridgeRetryValidator(retryMessage);
        if (retryMessage == "Q") {
            return false;
        }

        return true;
    }

    public boolean isGameFail() {
        return computerBridge.checkInputBridge(userBridge.getBridge());
    }

    public boolean isGameSuccess() {
        if (computerBridge.isEqual(userBridge.getBridge())) {
            gameSuccess = true;
            return true;
        }

        return false;
    }

    public ComputerBridge getComputerBridge() {
        return computerBridge;
    }

    public UserBridge getUserBridge() {
        return userBridge;
    }
}