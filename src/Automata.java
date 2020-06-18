import java.util.ArrayList;

class Pair{
    char a;
    int b;
    public Pair(char a , int b)
    {
        this.a = a;
        this.b = b;
    }
}

public class Automata {
    int N;
    ArrayList<Pair> adj[];
    boolean endState[];

    public Automata(int N)
    {
        N = N + 3;
        this.N = N;
        adj = new ArrayList[N];
        for(int i=0;i<N;++i)
            adj[i] = new ArrayList<>();
        endState = new boolean[N];
    }

    public void addArc(int currentState , char token , int nextState)
    {
        adj[currentState].add(new Pair(token , nextState));
    }

    public void addEndState(int end)
    {
        endState[end] = true;
    }

    String check(String input) {
        int currentState = 0;
        boolean ok = true;
        String path = "";
        for (int i = 0; i < input.length(); i++) {
            int lst = currentState;
            char token = input.charAt(i);
            currentState = nextState(currentState, token);
            path += "From State " + lst + " with input ( " + token + " ) to State " + currentState + "\n";
            if(currentState == -1)
            {
                ok = false;
                break;
            }
        }
        if(!ok)
            path = "false\n" + path;
        else
            path = endState[currentState] + "\n" + path;
        return path;
    }

    int nextState(int currentState , int input)
    {
        for(int i=0;i<adj[currentState].size();++i)
        {
            if(input == adj[currentState].get(i).a)
                return adj[currentState].get(i).b;
        }
        return -1;
    }

}
