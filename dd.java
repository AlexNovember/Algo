import java.util.Arrays;
import java.util.stream.IntStream;


// Узел связанного списка
class Node
{
    int data;
    Node next;
 
    Node(int data) {
        this.data = data;
    }
}
 
class Main
{
    // Вспомогательная функция для вставки нового узла в начало связанного списка
    public static Node push(Node head, int data)
    {
        Node node = new Node(data);
        node.next = head;
        return node;
    }
 
    // Функция для печати заданного связанного списка
    public static void printList(Node head)
    {
        Node ptr = head;
        while (ptr != null)
        {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
        System.out.println("null");
    }
 
 
    // Рекурсивная функция для обращения связанного списка.
    // Он переворачивает заданный связанный список, фиксируя указатель заголовка и
    // затем указатели `.next` каждого узла в обратном порядке
    public static Node reverse(Node head)
    {
        Node first, rest;
 
        // базовый случай пустого списка
        if (head == null) {
            return head;
        }
 
        first = head;               // предположим, что сначала = {1, 2, 3}
        rest = first.next;          // rest = {2, 3}
 
        // пустой базовый случай остатка
        if (rest == null) {
            return head;
        }
 
        rest = reverse(rest);       // рекурсивно инвертируем меньший случай {2, 3}
        // после: rest = {3, 2}
 
        first.next.next = first;    // помещаем первый элемент в конец списка
        first.next = null;          // (рисуем)
        head = rest;                // фиксируем указатель головы
 
        return head;
    }
 
    public static void main(String[] args)
    {
        // вводные данные
            int n = 15;
     
            int[] keys = IntStream.rangeClosed(1, n).toArray();
            System.out.println("Список: ");
            System.out.println(Arrays.toString(keys));

        Node head = null;
        for (int i = keys.length - 1; i >=0; i--) {
            head = push(head, keys[i]);
        }
 
        head = reverse(head);
        System.out.println("Развернутый список: ");
        printList(head);
    }
}