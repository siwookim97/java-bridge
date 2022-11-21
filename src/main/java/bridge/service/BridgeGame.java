package bridge.service;

import bridge.BridgeRandomNumberGenerator;
import bridge.util.Utils;
import bridge.util.validator.BridgeMakerValidator;
import bridge.util.validator.BridgeMoveValidator;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    private ComputerBridge computerBridge = new ComputerBridge();
    private UserBridge userBridge = new UserBridge();

    public void createBridge(String length) {
        new BridgeMakerValidator(length);
        computerBridge.setBridge(bridgeMaker.makeBridge(Utils.convertToInt(length)));
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String location) {
        new BridgeMoveValidator(location);
        userBridge.addBridge(location);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    public boolean isCorrectLocation() {
        if (computerBridge.getBridgeLast() == userBridge.getBridgeLast()) {
            return true;
        }
        return false;
    }

    public boolean isEndOfBridge() {
        if (isCorrectLocation() && (computerBridge.getBridgeLength() == userBridge.getBridgeLength())) {
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