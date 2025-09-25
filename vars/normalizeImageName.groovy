// Returns a sanitized Docker repo path like "org/myservice"
def call(String raw) {
  def s = (raw ?: '').trim().toLowerCase()
  s = s.replaceAll('[^a-z0-9._/-]', '-')   // keep only legal repo chars
  s = s.replaceAll('([._-])\\1+', '$1')    // collapse repeated separators
         .replaceAll('/{2,}', '/')
         .replaceAll('^([._-/])+', '')
         .replaceAll('([._-/])+$', '')
  if (!s) error("Sanitized IMAGE_NAME is empty; use 'myservice' or 'org/myservice'.")
  return s
}
