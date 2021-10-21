package rubroNegra;

import rubroNegra.impressao.Impressao;

public class BlackRed {
	   
    private No source;
    private No RNULL;

    public BlackRed() {
        RNULL = new No();
        RNULL.color = 0;
        RNULL.left = null;
        RNULL.right = null;
        source = RNULL;
    }

    private No min(No node) {
        while (node.left != RNULL) {
            node = node.left;
        }
        return node;
    }

    private No max(No node) {
        while (node.right != RNULL) {
            node = node.right;
        }
        return node;
    }

    private void leftRotation(No x) {
        No y = x.right;
        x.right = y.left;
        if (y.left != RNULL) {
            y.left.father = x;
        }
        y.father = x.father;
        if (x.father == null) {
            this.source = y;
        } else if (x == x.father.left) {
            x.father.left = y;
        } else {
            x.father.right = y;
        }
        y.left = x;
        x.father = y;
    }

    private void rightRotation(No x) {
        No y = x.left;
        x.left = y.right;
        if (y.right != RNULL) {
            y.right.father = x;
        }
        y.father = x.father;
        if (x.father == null) {
            this.source = y;
        } else if (x == x.father.right) {
            x.father.right = y;
        } else {
            x.father.left = y;
        }
        y.right = x;
        x.father = y;
    }

    private void replacementBlackRed(No u, No v) {
        if (u.father == null) {
            source = v;
        } else if (u == u.father.left) {
            u.father.left = v;
        } else {
            u.father.right = v;
        }
        v.father = u.father;
    }

    private void deleteCorrect(No x) {
        No s;
        while (x != source && x.color == 0) {
            if (x == x.father.left) {
                s = x.father.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.father.color = 1;
                    leftRotation(x.father);
                    s = x.father.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.father;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotation(s);
                        s = x.father.right;
                    }

                    s.color = x.father.color;
                    x.father.color = 0;
                    s.right.color = 0;
                    leftRotation(x.father);
                    x = source;
                }
            } else {
                s = x.father.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.father.color = 1;
                    rightRotation(x.father);
                    s = x.father.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.father;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotation(s);
                        s = x.father.left;
                    }

                    s.color = x.father.color;
                    x.father.color = 0;
                    s.left.color = 0;
                    rightRotation(x.father);
                    x = source;
                }
            }
        }
        x.color = 0;
    }

    private void delete(No node, int chave) {
        No z = RNULL;
        No x, y;
        while (node != RNULL) {
            if (node.value == chave) {
                z = node;
            }

            if (node.value <= chave) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == RNULL) {
            System.out.println("Não foi possível encontrar a chave na árvore");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == RNULL) {
            x = z.right;
            replacementBlackRed(z, z.right);
        } else if (z.right == RNULL) {
            x = z.left;
            replacementBlackRed(z, z.left);
        } else {
            y = min(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.father == z) {
                x.father = y;
            } else {
            	replacementBlackRed(y, y.right);
                y.right = z.right;
                y.right.father = y;
            }

            replacementBlackRed(z, y);
            y.left = z.left;
            y.left.father = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0) {
        	deleteCorrect(x);
        }
    }

    public void delete(int valor) {
    	delete(this.source, valor);
    }

    private void addCorrect(No k) {
        No u;
        while (k.father.color == 1) {
            if (k.father == k.father.father.right) {
                u = k.father.father.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.father.color = 0;
                    k.father.father.color = 1;
                    k = k.father.father;
                } else {
                    if (k == k.father.left) {
                        k = k.father;
                        rightRotation(k);
                    }
                    k.father.color = 0;
                    k.father.father.color = 1;
                    leftRotation(k.father.father);
                }
            } else {
                u = k.father.father.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.father.color = 0;
                    k.father.father.color = 1;
                    k = k.father.father;
                } else {
                    if (k == k.father.right) {
                        k = k.father;
                        leftRotation(k);
                    }
                    k.father.color = 0;
                    k.father.father.color = 1;
                    rightRotation(k.father.father);
                }
            }
            if (k == source) {
                break;
            }
        }
        source.color = 0;
    }

    public void add(int chave) {
    	No node = new No();
        node.father = null;
        node.value = chave;
        node.left = RNULL;
        node.right = RNULL;
        node.color = 1;

        No y = null;
        No x = this.source;

        while (x != RNULL) {
            y = x;
            if (node.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.father = y;
        if (y == null) {
            source = node;
        } else if (node.value < y.value) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.father == null) {
            node.color = 0;
            return;
        }

        if (node.father.father == null) {
            return;
        }

        addCorrect(node);
    }

    private No search(No node, int chave) {
        if (node == RNULL || chave == node.value) {
            return node;
        }

        if (chave < node.value) {
            return search(node.left, chave);
        }
        return search(node.right, chave);
    }

    public No search(int k) {
        return search(this.source, k);
    }

    private void print(No raiz, String indent, boolean last) {
        if (raiz != RNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = raiz.color == 1 ? "RED" : "BLACK";
            System.out.println(raiz.value + "(" + sColor + ")");
            print(raiz.left, indent, false);
            print(raiz.right, indent, true);
        }
    }

        public void print() {

          Impressao<No> p = new Impressao<>(n -> n.value + "(" + n.color() + ")", n -> n.left, n -> n.right);
          p.setSquareBranches(false);
          p.printTree(source);

    }
}