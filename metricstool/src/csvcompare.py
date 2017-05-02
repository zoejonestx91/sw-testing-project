import csv
import sys
import math

# python csvcompare.py TEST ACTUAL [ACTUAL2 ...]
# https://en.wikipedia.org/wiki/Symmetric_mean_absolute_percentage_error

count = 0
metrics = ["Package","Class","Name","COMP","NOA","VDEC","VREF","HLTH","HVOC","HVOL","HDIF","HEFF","HBUG","CAST","NOPR",
           "NAND","CREF","XMET","LMET","EXCR","EXCT","MOD","NLOC"]
acc = []
acci = 0

mapping = (
    (0, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 14),
    (5, 20),
    (6, 23),
    (7, 6),
    (8, 7),
    (9, 26),
    (10, 16),
    (11, 8),
    (12, 9),
    (13, 24),
    (14, 21),
    (15, 27),
    (16, 10),
    (17, 11),
    (18, 12),
    (19, 18),
    (20, 22),
    (21, 15),
    (22, 13)
)

def main():
    global acc
    global acci
    for i in range(0, len(sys.argv) - 2):
        acc.append([])

    while acci < len(sys.argv) - 2:
        test_file = open(sys.argv[1], "r")
        act_file = open(sys.argv[acci + 2], "r")
        test = csv.reader(test_file, delimiter=",")
        act = csv.reader(act_file, delimiter=",")

        for i in range(0, len(metrics)):
            acc[acci].append(0)

        first = True
        for colst in test:
            if first:
                first = False
                continue
            res = None
            firsta = True
            for colsa in act:
                if first:
                    first = False
                    continue
                # match method identities
                if colst[0].strip() == mapped(colsa, 0) and colst[1].strip() == mapped(colsa, 1) and colst[2].strip() == mapped(colsa, 2):
                    if res is not None:
                        res = None
                        break
                    res = compare(colst, colsa)

            record(res)
        acci += 1

    for i in range(0, acci):
        print(sys.argv[i + 2] + " & ", end="")
        for j in range(3, len(metrics)):
            val = (100 / count) * acc[i][j]
            if j == len(metrics) - 1:
                print(str(val), end="")
            else:
                print(str(val) + " & ", end="")
        print(" \\\\")


def compare(colst, colsa):
    res = []
    for i in range(0, len(metrics)):
        res.append(0)
    for i in range(3, len(metrics)):
        ai = float(mapped(colsa, i).strip())
        ti = float(colst[i].strip())
        if mapped(colsa, i) and colst[i].strip():
            res[i] = 0
        else:
            res[i] = abs(ai - ti) / (abs(ai) + abs(ti))
    return res


def record(res):
    if res is None:
        return
    global count
    count += 1
    for i in range(3, len(metrics)):
        acc[acci][i] += res[i]


def mapped(colsa, i):
    return colsa[mapping[i][1]].strip()


if __name__ == "__main__":
    if (len(sys.argv) < 3):
        print("Expect two CSV files at least.")
    main()