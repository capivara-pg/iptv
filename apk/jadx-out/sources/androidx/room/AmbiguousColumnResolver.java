package androidx.room;

import fa.f0;
import fa.n0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class AmbiguousColumnResolver {
    public static final AmbiguousColumnResolver INSTANCE = new AmbiguousColumnResolver();

    /* JADX INFO: Access modifiers changed from: private */
    static final class Match {
        private final List<Integer> resultIndices;
        private final va.f resultRange;

        public Match(va.f fVar, List<Integer> list) {
            kotlin.jvm.internal.m.g(fVar, "resultRange");
            kotlin.jvm.internal.m.g(list, "resultIndices");
            this.resultRange = fVar;
            this.resultIndices = list;
        }

        public final List<Integer> getResultIndices() {
            return this.resultIndices;
        }

        public final va.f getResultRange() {
            return this.resultRange;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ResultColumn {
        private final int index;
        private final String name;

        public ResultColumn(String str, int i) {
            kotlin.jvm.internal.m.g(str, "name");
            this.name = str;
            this.index = i;
        }

        public static /* synthetic */ ResultColumn copy$default(ResultColumn resultColumn, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = resultColumn.name;
            }
            if ((i2 & 2) != 0) {
                i = resultColumn.index;
            }
            return resultColumn.copy(str, i);
        }

        public final String component1() {
            return this.name;
        }

        public final int component2() {
            return this.index;
        }

        public final ResultColumn copy(String str, int i) {
            kotlin.jvm.internal.m.g(str, "name");
            return new ResultColumn(str, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResultColumn)) {
                return false;
            }
            ResultColumn resultColumn = (ResultColumn) obj;
            return kotlin.jvm.internal.m.b(this.name, resultColumn.name) && this.index == resultColumn.index;
        }

        public final int getIndex() {
            return this.index;
        }

        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.index;
        }

        public String toString() {
            return "ResultColumn(name=" + this.name + ", index=" + this.index + ')';
        }
    }

    private static final class Solution implements Comparable<Solution> {
        public static final Companion Companion = new Companion(null);
        private static final Solution NO_SOLUTION = new Solution(fa.p.h(), Integer.MAX_VALUE, Integer.MAX_VALUE);
        private final int coverageOffset;
        private final List<Match> matches;
        private final int overlaps;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
                this();
            }

            public final Solution build(List<Match> list) {
                boolean z;
                kotlin.jvm.internal.m.g(list, "matches");
                int i = 0;
                int iC = 0;
                for (Match match : list) {
                    iC += ((match.getResultRange().c() - match.getResultRange().a()) + 1) - match.getResultIndices().size();
                }
                Iterator<T> it = list.iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                int iA = ((Match) it.next()).getResultRange().a();
                while (it.hasNext()) {
                    int iA2 = ((Match) it.next()).getResultRange().a();
                    if (iA > iA2) {
                        iA = iA2;
                    }
                }
                Iterator<T> it2 = list.iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                int iC2 = ((Match) it2.next()).getResultRange().c();
                while (it2.hasNext()) {
                    int iC3 = ((Match) it2.next()).getResultRange().c();
                    if (iC2 < iC3) {
                        iC2 = iC3;
                    }
                }
                Collection fVar = new va.f(iA, iC2);
                if (!(fVar instanceof Collection) || !fVar.isEmpty()) {
                    f0 it3 = fVar.iterator();
                    int i2 = 0;
                    while (it3.hasNext()) {
                        int iA3 = it3.a();
                        Iterator<T> it4 = list.iterator();
                        int i3 = 0;
                        while (true) {
                            if (!it4.hasNext()) {
                                z = false;
                                break;
                            }
                            if (((Match) it4.next()).getResultRange().g(iA3)) {
                                i3++;
                            }
                            if (i3 > 1) {
                                z = true;
                                break;
                            }
                        }
                        if (z && (i2 = i2 + 1) < 0) {
                            fa.p.o();
                        }
                    }
                    i = i2;
                }
                return new Solution(list, iC, i);
            }

            public final Solution getNO_SOLUTION() {
                return Solution.NO_SOLUTION;
            }
        }

        public Solution(List<Match> list, int i, int i2) {
            kotlin.jvm.internal.m.g(list, "matches");
            this.matches = list;
            this.coverageOffset = i;
            this.overlaps = i2;
        }

        public final int getCoverageOffset() {
            return this.coverageOffset;
        }

        public final List<Match> getMatches() {
            return this.matches;
        }

        public final int getOverlaps() {
            return this.overlaps;
        }

        @Override // java.lang.Comparable
        public int compareTo(Solution solution) {
            kotlin.jvm.internal.m.g(solution, "other");
            int i = kotlin.jvm.internal.m.i(this.overlaps, solution.overlaps);
            return i != 0 ? i : kotlin.jvm.internal.m.i(this.coverageOffset, solution.coverageOffset);
        }
    }

    /* JADX INFO: renamed from: androidx.room.AmbiguousColumnResolver$resolve$4, reason: invalid class name */
    static final class AnonymousClass4 extends kotlin.jvm.internal.n implements pa.l {
        final /* synthetic */ kotlin.jvm.internal.a0 $bestSolution;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(kotlin.jvm.internal.a0 a0Var) {
            super(1);
            this.$bestSolution = a0Var;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((List<Match>) obj);
            return ea.v.a;
        }

        public final void invoke(List<Match> list) {
            kotlin.jvm.internal.m.g(list, "it");
            Solution solutionBuild = Solution.Companion.build(list);
            if (solutionBuild.compareTo((Solution) this.$bestSolution.a) < 0) {
                this.$bestSolution.a = solutionBuild;
            }
        }
    }

    private AmbiguousColumnResolver() {
    }

    private final <T> void dfs(List<? extends List<? extends T>> list, List<T> list2, int i, pa.l lVar) {
        if (i == list.size()) {
            lVar.invoke(fa.p.i0(list2));
            return;
        }
        Iterator<T> it = list.get(i).iterator();
        while (it.hasNext()) {
            list2.add(it.next());
            INSTANCE.dfs(list, list2, i + 1, lVar);
            fa.p.w(list2);
        }
    }

    static /* synthetic */ void dfs$default(AmbiguousColumnResolver ambiguousColumnResolver, List list, List list2, int i, pa.l lVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            list2 = new ArrayList();
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        ambiguousColumnResolver.dfs(list, list2, i, lVar);
    }

    private final void rabinKarpSearch(List<ResultColumn> list, String[] strArr, pa.q qVar) {
        int i = 0;
        int iHashCode = 0;
        for (String str : strArr) {
            iHashCode += str.hashCode();
        }
        int length = strArr.length;
        Iterator<T> it = list.subList(0, length).iterator();
        int iHashCode2 = 0;
        while (it.hasNext()) {
            iHashCode2 += ((ResultColumn) it.next()).getName().hashCode();
        }
        while (true) {
            if (iHashCode == iHashCode2) {
                qVar.invoke(Integer.valueOf(i), Integer.valueOf(length), list.subList(i, length));
            }
            i++;
            length++;
            if (length > list.size()) {
                return;
            } else {
                iHashCode2 = (iHashCode2 - list.get(i - 1).getName().hashCode()) + list.get(length - 1).getName().hashCode();
            }
        }
    }

    public static final int[][] resolve(String[] strArr, String[][] strArr2) {
        boolean z;
        kotlin.jvm.internal.m.g(strArr, "resultColumns");
        kotlin.jvm.internal.m.g(strArr2, "mappings");
        int length = strArr.length;
        int i = 0;
        while (true) {
            z = true;
            if (i >= length) {
                break;
            }
            String strSubstring = strArr[i];
            if (strSubstring.charAt(0) == '`' && strSubstring.charAt(strSubstring.length() - 1) == '`') {
                strSubstring = strSubstring.substring(1, strSubstring.length() - 1);
                kotlin.jvm.internal.m.f(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            Locale locale = Locale.US;
            kotlin.jvm.internal.m.f(locale, "US");
            String lowerCase = strSubstring.toLowerCase(locale);
            kotlin.jvm.internal.m.f(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            strArr[i] = lowerCase;
            i++;
        }
        int length2 = strArr2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            int length3 = strArr2[i2].length;
            for (int i3 = 0; i3 < length3; i3++) {
                String[] strArr3 = strArr2[i2];
                String str = strArr3[i3];
                Locale locale2 = Locale.US;
                kotlin.jvm.internal.m.f(locale2, "US");
                String lowerCase2 = str.toLowerCase(locale2);
                kotlin.jvm.internal.m.f(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                strArr3[i3] = lowerCase2;
            }
        }
        Set setB = n0.b();
        for (String[] strArr4 : strArr2) {
            fa.p.u(setB, strArr4);
        }
        Set setA = n0.a(setB);
        List listC = fa.p.c();
        int length4 = strArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length4) {
            String str2 = strArr[i4];
            int i6 = i5 + 1;
            if (setA.contains(str2)) {
                listC.add(new ResultColumn(str2, i5));
            }
            i4++;
            i5 = i6;
        }
        List<ResultColumn> listA = fa.p.a(listC);
        int length5 = strArr2.length;
        ArrayList arrayList = new ArrayList(length5);
        for (int i7 = 0; i7 < length5; i7++) {
            arrayList.add(new ArrayList());
        }
        int length6 = strArr2.length;
        int i8 = 0;
        int i9 = 0;
        while (i8 < length6) {
            String[] strArr5 = strArr2[i8];
            int i10 = i9 + 1;
            INSTANCE.rabinKarpSearch(listA, strArr5, new AmbiguousColumnResolver$resolve$1$1(strArr5, arrayList, i9));
            if (((List) arrayList.get(i9)).isEmpty()) {
                ArrayList arrayList2 = new ArrayList(strArr5.length);
                for (String str3 : strArr5) {
                    List listC2 = fa.p.c();
                    for (ResultColumn resultColumn : listA) {
                        if (kotlin.jvm.internal.m.b(str3, resultColumn.getName())) {
                            listC2.add(Integer.valueOf(resultColumn.getIndex()));
                        }
                    }
                    List listA2 = fa.p.a(listC2);
                    if (!(!listA2.isEmpty())) {
                        throw new IllegalStateException(("Column " + str3 + " not found in result").toString());
                    }
                    arrayList2.add(listA2);
                }
                dfs$default(INSTANCE, arrayList2, null, 0, new AmbiguousColumnResolver$resolve$1$2(arrayList, i9), 6, null);
            }
            i8++;
            i9 = i10;
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!(!((List) it.next()).isEmpty())) {
                    z = false;
                    break;
                }
            }
        }
        if (!z) {
            throw new IllegalStateException("Failed to find matches for all mappings".toString());
        }
        kotlin.jvm.internal.a0 a0Var = new kotlin.jvm.internal.a0();
        a0Var.a = Solution.Companion.getNO_SOLUTION();
        dfs$default(INSTANCE, arrayList, null, 0, new AnonymousClass4(a0Var), 6, null);
        List<Match> matches = ((Solution) a0Var.a).getMatches();
        ArrayList arrayList3 = new ArrayList(fa.p.q(matches, 10));
        Iterator<T> it2 = matches.iterator();
        while (it2.hasNext()) {
            arrayList3.add(fa.p.h0(((Match) it2.next()).getResultIndices()));
        }
        Object[] array = arrayList3.toArray(new int[0][]);
        kotlin.jvm.internal.m.e(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (int[][]) array;
    }
}
