import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Resource<list> {
    private List<Long> list = new ArrayList<>();
    private List<Long> fullList = Collections.synchronizedList(list);

    public List<Long> getList() {
        return list;
    }

    public List<Long> getFullList() {
        return fullList;
    }

    public void setFullList(List<Long> fullList) {
        this.fullList = fullList;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public synchronized Long take() throws InterruptedException {
        while (fullList.isEmpty() ) {
                wait();

        }
        return fullList.remove(0);
    }

    public synchronized void put(Long value) {
        fullList.add(value);
        notifyAll();
    }
}
