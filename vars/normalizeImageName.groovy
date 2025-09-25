// Returns a sanitized Docker repo path like "org/myservice"
def call(String raw) {
  def s = (raw ?: '').trim().toLowerCase()

  // keep only legal repo chars
  s = s.replaceAll('[^a-z0-9._/-]', '-')

  // collapse repeated separators and slashes
  s = s.replaceAll('([._-])\\1+', '$1')
       .replaceAll('/{2,}', '/')

  // trim leading/trailing separators or slashes (hyphen first in class!)
  s = s.replaceAll('^[-._/]+', '')
       .replaceAll('[-._/]+$', '')

  if (!s) error("Sanitized IMAGE_NAME is empty; use 'myservice' or 'org/myservice'.")
  return s
}
