
import javafx.scene.chart.*;
import java.util.*;
import javafx.collections.*;

public class PieGene
{
    private ObservableList<PieChart.Data> obsList = FXCollections.observableArrayList();
    public boolean containsBase(String base)
    {
        Iterator<PieChart.Data> iter = obsList.iterator();
        while (iter.hasNext())
        {
            PieChart.Data next = iter.next();
            if (next.getName().equals(base))
            {
                return true;
            }
        }
        return false;
    }
    
    public ObservableList<PieChart.Data> getObsList()
    {
        return obsList;
    }
    
    public void addChem(String base, int amt)
    {
        if (containsBase(base) == true)
        {
            Iterator<PieChart.Data> iter = obsList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals(base))
                {
                    double old_amt = next.getPieValue();
                    next.setPieValue(old_amt + amt);
                }
            }
        }
        else
        {
            obsList.add(new PieChart.Data(base, amt));
        }
    }
    
    public void removeChem(String base, int amt)
    {
        if (containsBase(base) == true)
        {
            Iterator<PieChart.Data> iter = obsList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (next.getName().equals(base)) {
                    double old_amt = next.getPieValue();
                    double diff = old_amt - amt;
                    next.setPieValue(old_amt - amt);
                    if (diff <= 0) {
                        obsList.remove(next);
                    }
                }
            }
        }
    }
    
    public String getCompositions()
    {
        if (obsList.isEmpty())
        {
            return "None";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<PieChart.Data> iter = obsList.iterator();
        while (iter.hasNext())
        {
            PieChart.Data next = iter.next();
            sb.append(next.getName() + ": " + next.getPieValue() + "; ");
        }
        return sb.toString();
    }
}
