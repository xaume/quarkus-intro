http $(oc get route quarkus-integration-example-route -n vlc-quarkus-integration --template='{{ .spec.host }}')/greetings text="hello valencia"