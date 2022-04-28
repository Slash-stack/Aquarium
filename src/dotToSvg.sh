#! /bin/sh

cd ~/ensimag/2A/projet-spe/Aquarium/ || exit 1

cd output/dotFiles || exit 1

for filename in *.dot
do
  dot -Tsvg "$filename" > "../svgFiles/$filename.svg"
done

