package ed.lab;

import com.github.afkbrb.btp.BTPrinter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.commons.util.StringUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class E01FlattenBTTest {

    @ParameterizedTest
    @CsvFileSource(resources = "E01.csv", useHeadersInDisplayName = true, delimiter = '|')
    void flatten(String input, String expectedOutput) {
        final TreeNode<Integer> root = BTUtils.parseBT(input, Integer::parseInt);
        final TreeNode<Integer> expected = BTUtils.parseBT(expectedOutput, Integer::parseInt);

        E01FlattenBT e01 = new E01FlattenBT();

        e01.flatten(root);

        System.out.print("""
                Input:
                """);
        BTPrinter.printTree(cleanUp(input));

        System.out.print("""
                Resultado Esperado:
                """);
        BTPrinter.printTree(cleanUp(expectedOutput));

        System.out.print("""
                Resultado Obtenido:
                """);
        BTPrinter.printTree(BTUtils.levelOrder(root));

        similarTrees(root, expected);
    }

    private void similarTrees(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if (root1 == null) {
            assertNull(root2,
                    String.format("Se esperaba un nodo null y se encontró [%s]", root2));
            return;
        }

        assertNotNull(root2,
                String.format("Se esperaba [%s] y se encontró un nodo nulo", root1));

        assertEquals(root1.value, root2.value,
                String.format("Se esperaba [%s] y se encontró [%s]", root1, root2));

        similarTrees(root1.left, root2.left);
        similarTrees(root1.right, root2.right);
    }

    private String cleanUp(String input) {
        return Optional.ofNullable(input)
                .map(String::trim)
                .map(str -> str.replaceAll("\\.", "#"))
                .orElse("#");
    }
}