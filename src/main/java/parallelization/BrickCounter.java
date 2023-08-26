package parallelization;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

public class BrickCounter {
    /**
     * Vous pouvez ajouter de nouvelles méthodes, classes internes,
     * etc., mais vous ne pouvez pas modifier la signature
     * (paramètres, exceptions, etc.)  des méthodes existantes ou le
     * type des membres existants.
     */

    /**
     * You can add new methods, inner classes, etc. but do not modify
     * the signature (parameters, exceptions, etc.) of the existing
     * methods or the types of existing fields.
     */

    public interface Brick {
        String getColor();

        int getSize();
    }


    /**
     * Imaginez un amateur de Lego qui possède un grand nombre de
     * briques Lego (paramètre "bricks") qu'il souhaite placer dans un
     * nombre donné de boîtes (paramètre "n"). Le fan de Lego veut
     * savoir combien de briques vont dans la première boîte (boîte
     * 0), combien de briques vont dans la deuxième boîte (boîte 1),
     * etc. Pour décider à quelle boîte une brique appartient, le fan
     * de Lego utilise une fonction "sorter" qui renvoie pour une
     * brique le numéro de sa boîte.
     *
     * Ecrivez la méthode "countBricks" qui renvoie un tableau
     *   [ a_0, a_1, a_2, ..., a_n-1]
     * où l'élément a_i signifie que a_i briques vont dans la boîte i.
     *
     * Voir le test "testThreeBricks" pour un exemple.
     *
     * Complexité temporelle:
     *     Votre méthode doit s'executer en O(max(n,bricks.length))
     *
     * Vous pouvez supposer que :
     *    - n est toujours supérieur à 0
     *    - la fonction "sorter" renvoie toujours un nombre compris entre 0 et n-1.
     */

    /**
     * Imagine a Lego fan who has a large number of Lego bricks
     * (parameter "bricks") that they want to put into a given number
     * of boxes (parameter "n").  The Lego fan wants to know how many
     * bricks go into the first box (box 0), how many bricks go into
     * the second box (box 1), etc.  To decide in which box a brick
     * belongs, the Lego fan uses a "sorter" function. The sorter
     * function returns for a brick the box number.
     *
     * Write the method "countBricks" that returns an array
     *   [ a_0, a_1, a_2, ..., a_n-1]
     * where element a_i means that a_i bricks go into box i.
     *
     * See the test "testThreeBricks" for an example.
     *
     * Time complexity:
     *      Your method should execute in O(max(n,bricks.length))
     *
     * You can assume:
     *    - n is always greater than 0
     *    - the function "sorter" always returns a number between 0 and n-1.
     *
     * @param bricks  the bricks to count
     * @param n the number of boxes
     * @param sorter the function tells in which box a brick belongs
     * @return an array with the number of bricks per box
     */
    public static int[] countBricks(Brick[] bricks, int n, Function<Brick, Integer> sorter) {
        // TODO
         return null;
    }


    /**
     * Écrivez la méthode "countBricksTwoThreads". Cette méthode fait
     * la même chose que la méthode "countBricks".  Cependant, la
     * méthode DOIT utiliser deux "Future" d'un thread pool pour
     * accélérer le comptage des briques.
     *
     * ATTENTION :
     *   - Vous DEVEZ utiliser deux threads. Vous obtiendrez 0 point
     *     pour cette méthode si vous n'utilisez pas le thread pool
     *     donné en argument, même si votre code renvoie le bon
     *     résultat sur inginious !
     *   - Les calculs doivent être équilibrés entre les deux threads (c'est-à-dire
     *     que les deux threads doivent faire à peu près la même quantité de travail).
     *   - Le paramètre "executor" correspond au "thread pool" a
     *     utiliser. Vous ne devez *pas* appeler vous-memes la methode
     *     "Executors.newFixedThreadPool()" pour creer un thread pool,
     *     ni la methode "executor.shutdown()" (cela est deja fait
     *     pour vous dans les tests unitaires).
     *   - Vous DEVEZ attraper toutes les exceptions. Renvoyez "null" en
     *     cas de problème.
     */

    /**
     * Write the method "countBricksTwoThreads". The method does the
     * same thing as the method "countBricks".  However, the method
     * MUST use two "Future" of a thread pool to accelerate the brick
     * counting.
     *
     * WARNING:
     *   - You MUST use two threads.  You will get 0 points for this
     *     method if you don't use threads, even if your code returns
     *     the correct result on inginious!
     *   - The computations must be balanced between the two threads
     *     (i.e. they both must do roughly the same amount of work)
     *   - The "executor" parameter corresponds to the thread pool to
     *     use. You *don't have* to call the
     *     "Executors.newFixedThreadPool()" method by yourself to
     *     create the thread pool, nor the "executor.shutdown()"
     *     method (this is already done for you in the unit tests).
     *   - You MUST catch all exceptions. You can return null if there is a problem.
     */
    public static int[] countBricksTwoThreads(Brick[] bricks, int n, Function<Brick, Integer> sorter,
                                              ExecutorService executor) {
        // TODO
         return null;
    }
}
