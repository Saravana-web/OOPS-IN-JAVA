import java.util.*;

class SE extends Exception {
    SE(String m) { super(m); }
}

class Emp {
    String n; int i; double net;
    Emp(String n, int i, double b, double a, double d)
            throws SE {
        if (b < 0 || a < 0 || d < 0)
            throw new SE("Salary components must not be negative.");
        net = b + a - d;
        if (net < 0)
            throw new SE("Net salary cannot be negative.");
        this.n = n; this.i = i;
    }

    public String toString() {
        return "Employee ID: " + i + ", Name: " + n
                + ", Net Salary: ₹" + net;
    }
}

class AS<T> implements Iterable<T> {
    T[] a; int t = -1;
    @SuppressWarnings("unchecked")
    AS(int s) { a = (T[]) new Object[s]; }

    void p(T x) {
        if (t == a.length - 1) {
            System.out.println("Stack Overflow!");
            return;
        }
        a[++t] = x;
    }

    T o() {
        if (t == -1) {
            System.out.println("Stack Underflow!");
            return null;
        }
        return a[t--];
    }

    boolean e() { return t == -1; }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            int c = t;

            public boolean hasNext() { return c >= 0; }

            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return a[c--];
            }
        };
    }
}

public class ex4b {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        AS<Emp> st = new AS<>(100);

        while (true) {
            System.out.println("\n1.Add Employee Payroll");
            System.out.println("2.Undo Last Entry");
            System.out.println("3.View Payroll History");
            System.out.println("4.Exit");
            System.out.print("Choose an option: ");

            int c;
            try {
                c = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (c) {
                case 1 -> {
                    try {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(s.nextLine());
                        System.out.print("Name: ");
                        String n = s.nextLine();
                        System.out.print("Basic: ");
                        double b = Double.parseDouble(s.nextLine());
                        System.out.print("Allow: ");
                        double a = Double.parseDouble(s.nextLine());
                        System.out.print("Deduct: ");
                        double d = Double.parseDouble(s.nextLine());

                        Emp e = new Emp(n, id, b, a, d);
                        st.p(e);
                        System.out.println("Payroll added: " + e);
                    } catch (NumberFormatException e) {
                        System.out.println("Enter valid numbers.");
                    } catch (SE e) {
                        System.out.println("Salary error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    if (!st.e())
                        System.out.println("Removed: " + st.o());
                    else
                        System.out.println("No entries to undo.");
                }
                case 3 -> {
                    if (st.e())
                        System.out.println("No payroll entries found.");
                    else {
                        System.out.println("Payroll History:");
                        for (Emp e : st)
                            System.out.println(e);
                    }
                }
                case 4 -> {
                    System.out.println("Exiting.");
                    s.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

