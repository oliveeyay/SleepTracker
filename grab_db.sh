#!/bin/sh
elementIn () {
  local e
  for e in "${@:2}"; do [[ "$e" == "$1" ]] && return 0; done
  return 1
}
set -e
# get arguments
args=("$@")
if elementIn "-h" "${args[@]}" || elementIn "-help" "${args[@]}"
then
  printf "\nYou can call this script to pull and decrypt the SQLite database on debug or release builds: \n\n\n"
  printf "      -p        Grab release database, if not set defaults to debug database\n"
  printf "      -u        If adb cannot pull from app dir, will move files to /sdcard/ before pull\n"
  printf "      -h -help  To see this message\n\n"
  exit 1
fi
printf "===================Grabbing Database from Android===================\n"
printf "closing SQLPro reader if open\n"
osascript -e 'quit app "SQLPro for SQLite Read-Only"'

printf "removing previous databases, if existent..\n"
rm -rf sleep_tracker_encrypted.db
rm -rf sleep_tracker_decrypted.db

package=""

if elementIn "-p" "${args[@]}"
then
  package=com.og.health.sleeptracker
else
  package=com.og.health.sleeptracker
fi
printf "Using Package:  " $package "\n"

printf "pulling sleep_tracker_encrypted.db from device..\n"
if elementIn "-u" "${args[@]}"
then
  adb shell "su -c 'cp /data/data/"$package"/databases/sleep_tracker_encrypted.db /sdcard/'"
  adb pull /sdcard/sleep_tracker_encrypted.db
  printf "pulling SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml...\n"
  adb shell "su -c 'cp /data/data/"$package"/shared_prefs/SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml /sdcard/'"
  adb pull /sdcard/SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml
else
  adb pull /data/data/$package/databases/sleep_tracker_encrypted.db

  printf "pulling SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml...\n"
  adb pull /data/data/$package/shared_prefs/SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml
fi



MY_KEY=$(xml sel -t -v '/map/string[@name="SLEEP_TRACKER_SEED_KEY"]' SLEEP_TRACKER_SEED_SHARED_PREFERENCES.xml)

printf "Using Decryption Key ::  " ${MY_KEY} "\n"

printf "decrypting sleep_tracker_encrypted.db into sleep_tracker_decrypted.db\n"
sqlcipher -line sleep_tracker_encrypted.db 'PRAGMA key="'${MY_KEY}'";ATTACH DATABASE "sleep_tracker_decrypted.db" AS sleep_tracker_decrypted KEY "";SELECT sqlcipher_export("sleep_tracker_decrypted");DETACH DATABASE sleep_tracker_decrypted;'

open sleep_tracker_decrypted.db
