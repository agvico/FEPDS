# Evolutionary Fuzzy Emerging Patterns Extraction for Massive Data Streams

In this repository, the source code of the FEPMDS algorithm is stored. Please, cite this work as:

*citation not available yet*
## Compile

SBT is employed in this project for a better management of dependencies. We also use the sbt-assembly plugin for the generation of a fat-jar that produces a single jar file that contains all the necessary dependencies. Assuming sbt is already installed on your system, for compiling the program just execute the following commands in a terminal:

```
sbt clean
sbt compile
sbt assembly
```
The final generated .jar file is under the /target directory.

## Data format

Data must follow the ARFF dataset format (https://www.cs.waikato.ac.nz/~ml/weka/arff.html).

## Parameters

Parameters of the FEPMDS algorithm are introduced following a Linux-CLI style. For the complete list of commands, run the method with the *-h* option. The complete list of parameters are summarised below:

```
Usage: <main class> [-BhSv] [--kafkabroker=NAME] [--maxSamples=Number]
                    [--time=SECONDS] [-c=<CROSSOVER_PROBABILITY>] [-C=VALUE]
                    [-e=VALUE] [-l=NUMBER] [-m=<MUTATION_PROBABILITY>]
                    [-n=PARTITIONS] [-p=VALUE] [-Q=SIZE] [-r=PATH] [-t=PATH]
                    [-T=PATH] [--topics=NAME(S)]... [-o=NAME(S)[,NAME
                    (S)...]]... trainingFile testFile
      trainingFile          The training file in ARFF format.
      testFile              The test file in ARFF format.
      --kafkabroker=NAME    The host an port of the kafka broker being used
      --maxSamples=Number   The maximum number of samples to process before stop the
                              process
      --time=SECONDS        Data collect time (in milliseconds) for the Spark
                              Streaming engine
      --topics=NAME(S)      A comma-separated list of kafka topics to be employed
  -B                        Big Data processing using Spark
  -c, --crossover=<CROSSOVER_PROBABILITY>
                            The crossover probability. By default is 0.6
  -C=VALUE                  Chunk size for non-big data streaming processing
  -e=VALUE                  Maximum number of evaluations
  -h, --help                Show this help message and exit.
  -l, --labels=NUMBER       The number of fuzzy linguistic labels for each variable.
  -m, --mutation=<MUTATION_PROBABILITY>
                            The mutation probability. By default is 0.1
  -n=PARTITIONS             The number of partitions employed for Big Data
  -o, --objectives=NAME(S)[,NAME(S)...]
                            A comma-separated list of quality measures to be used as
                              objectives
  -p=VALUE                  Population Size
  -Q=SIZE                   The size of the FIFO queue of FEPDS
  -r, --rules=PATH          The path for storing the rules file.
  -S                        Streaming processing
  -t, --training=PATH       The path for storing the training results file.
  -T, --test=PATH           The path for storing the test results file.
  -v                        Show INFO messages.
```


## Execution

The execution of the method using Apache Kafka and Apache Spark Streaming is performed by means of setting the *-B* and *-S* flags. In addition, the training file will contain only the ARFF header file. No test file should be provided as the methods follows a test-then-train approache. Threfore, test file must be *null*. 

The most bases execution of FEPMDS under a Spark Standalone  cluster is as follows:
```
spark-submit --master $MASTER_URL FEPMDS.jar -BS headerFile.arff null
```

This will execute the algorithm with the default parameters, waiting for the reception of data on the Kafka topic called *test*.
## Study


~~To execute the experimental carried out in the paper, please check the release and download it. Then, decompress the study.tar.xz file and execute the _studyReplication.sh_ script.~~
