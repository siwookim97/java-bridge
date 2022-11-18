package bridge.controller;

import bridge.BridgeRandomNumberGenerator;
import bridge.service.BridgeMaker;
import bridge.util.Utils;
import bridge.util.validator.BridgeMakerValidator;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    public void start() {
        OutputView.printStartGame();
        createBridge();
    }

    public List<String> createBridge() {
        String length = InputView.readBridgeSize();
        new BridgeMakerValidator(length);
        return bridgeMaker.makeBridge(Utils.convertToInt(length));
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move() {
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}
