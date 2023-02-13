git add .
git commit -m "New Changes"
git push

cd ../xml2database-deploys
dirlist=$(find $1 -mindepth 1 -maxdepth 1 -type d)

for dir in $dirlist
do
  (
    cd $dir
    git pull

    cd src/main/java/com/cwiztech
    dirlist1=$(find $1 -mindepth 1 -maxdepth 1 -type d)
    for dir1 in $dirlist1
    do
      (
        rm -r $dir1
      )
    done
    
    cd ../../../../../../../xml2database/src/main/java/com/cwiztech
    dirlist1=$(find $1 -mindepth 1 -maxdepth 1 -type d)
    for dir1 in $dirlist1
    do
      (
        echo $dir1
	cd ../../../../../../xml2database-deploys
	cd $dir/src/main/java/com/cwiztech/
        cp -r ../../../../../../../xml2database/src/main/java/com/cwiztech/$dir1 .
      )
    done

    cd ../../../../../../xml2database-deploys

    cd $dir
    git add .
    git commit -m "New Changes"
    git push
  )
done

