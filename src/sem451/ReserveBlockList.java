
package sem451;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReserveBlockList {
	

	    private List<ReserveBlock> reserveBlockList;
	    private Map<String, ReserveBlock> reserveBlockMap;
	    private boolean useListStorage;

	    public ReserveBlockList(boolean useListStorage) {
	        this.useListStorage = useListStorage;
	        if (useListStorage) {
	            reserveBlockList = new ArrayList<>();
	        } else {
	            reserveBlockMap = new HashMap<>();
	        }
	    }

	    public boolean addReserveBlock(ReserveBlock block) {
	        if (useListStorage) {
	            reserveBlockList.add(block);
	            return true;
	        } else {
	            String key = block.getDate() + "_" + block.getClock() + "_" + block.getRoom().getName();
	            if (reserveBlockMap.containsKey(key)) {
	                return false;
	            }
	            reserveBlockMap.put(key, block);
	            return true;
	        }
	    }

	    public boolean removeReserveBlock(ReserveBlock block) {
	        if (useListStorage) {
	            return reserveBlockList.remove(block);
	        } else {
	            String key = block.getDate() + "_" + block.getClock() + "_" + block.getRoom().getName();
	            return reserveBlockMap.remove(key) != null;
	        }
	    }

	    public List<ReserveBlock> findReserveBlocksByDate(LocalDate date) {
	        if (useListStorage) {
	            return reserveBlockList.stream()
	                                   .filter(rb -> rb.getDate().equals(date))
	                                   .collect(Collectors.toList());
	        } else {
	            return reserveBlockMap.values().stream()
	                                  .filter(rb -> rb.getDate().equals(date))
	                                  .collect(Collectors.toList());
	        }
	    }

	    public void printAllReserveBlocks() {
	        if (useListStorage) {
	            reserveBlockList.forEach(System.out::println);
	        } else {
	            reserveBlockMap.values().forEach(System.out::println);
	        }
	    }

	    public int countReserveBlocks() {
	        return useListStorage ? reserveBlockList.size() : reserveBlockMap.size();
	    }

	    public void clearAllReserveBlocks() {
	        if (useListStorage) {
	            reserveBlockList.clear();
	        } else {
	            reserveBlockMap.clear();
	        }
	    }

	    public ReserveBlock getReserveBlock(LocalDate date, int clock, Room room) {
	        if (!useListStorage) {
	            String key = date + "_" + clock + "_" + room.getName();
	            return reserveBlockMap.get(key);
	        }
	        return null;
	    }




}
